
package com.calculatorservice.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Operation> calculateAddingPercentage(Double paramA, Double paramB) {

		Double percentage = providerConnector.getPercentageIndex();
		// (5 + 5) + 10% = 11).
		Double result = (paramA + paramB) + ((paramA + paramB) * percentage / 100);
		Operation op = new Operation();
		StringBuffer sb = new StringBuffer();
		sb.append("( ");
		sb.append(paramA);
		sb.append(" + ");
		sb.append(paramB);
		sb.append(" )");
		sb.append(" / ");
		sb.append(percentage + "% ");
		sb.append(" = ");
		sb.append(result);
		op.setOperation(sb.toString());

		List<Operation> resultList = getAll();
		resultList.add(0, op);
		addUnsincronized(op);
		return resultList;
	}

	@Override
	public List<Operation> getAll() {
		return calculatorRepository.findAll();
	}

	@Override
	public void addUnsincronized(Operation operation) {
		new Thread(() -> {
			calculatorRepository.save(operation);
			List<Operation> operations = calculatorRepository.findAll();

		}).start();

	}

}
