
package com.calculatorservice.services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.calculator.exceptions.RemoteException;
import com.calculator.exceptions.ServiceException;
import com.calculatorservice.entities.Operation;
import com.calculatorservice.remote.ProviderConnector;
import com.calculatorservice.repository.CalculatorRepository;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Autowired
	private ProviderConnector providerConnector;

	@Autowired
	private CalculatorRepository calculatorRepository;

	@Override
	public List<Operation> calculateAddingPercentage(Double paramA, Double paramB,int page, int size) throws ServiceException{
		List<Operation> resultList = new ArrayList<Operation>();
		Double percentage= null;
		try {
			percentage = providerConnector.getPercentageIndex();
			// (5 + 5) + 10% = 11).
			Double result = (paramA + paramB) + ((paramA + paramB) * percentage / 100);
			Operation op = new Operation();
			StringBuffer sb = new StringBuffer();
			sb.append("( ");
			sb.append(paramA);
			sb.append(" + ");
			sb.append(paramB);
			sb.append(" )");
			sb.append(" + ");
			sb.append(percentage + "% ");
			sb.append(" = ");
			sb.append(result);
			op.setOperation(sb.toString());
			op.setDate(new Date());
			
			//PUEDE SER QUE TODAVIA NO HAYA NADA INSERTADO
			//PUDE SER QUE LA CONSULTA DE LA PAGINA NO TENGA NADA
			
			if(page>=0)page--;
			Page<Operation> query= findAll(page, size);
			if(query.getTotalElements()==0 && page==0) {
				resultList.add(0, op);
			}else {
				resultList = new ArrayList<Operation>(query.getContent());
			}
			addUnsincronized(op);
		}catch (RemoteException e) {
			throw new ServiceException(e);
		}
		return resultList;
	}

	
	
	
	@Override
	public void addUnsincronized(Operation operation) {
		new Thread(() -> {
			calculatorRepository.save(operation);			
		}).start();

	}
	
	
	public Page<Operation> findAll(int page, int size)
	{	
		return calculatorRepository.findAll(PageRequest.of(page, size,Sort.by("date").descending()));
		
				
		
	}

}
