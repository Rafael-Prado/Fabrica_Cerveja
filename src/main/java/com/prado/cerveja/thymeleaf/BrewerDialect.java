package com.prado.cerveja.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.prado.cerveja.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.prado.cerveja.thymeleaf.processor.MenuTagProcessor;
import com.prado.cerveja.thymeleaf.processor.MessageElementTagProcessor;
import com.prado.cerveja.thymeleaf.processor.OrderElementTagProcessor;
import com.prado.cerveja.thymeleaf.processor.PaginationElementTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect{

	public  BrewerDialect() {
		super("Prado Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
		
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuTagProcessor(dialectPrefix));
		return processadores;
	}

}
