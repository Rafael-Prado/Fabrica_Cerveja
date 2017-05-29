package com.prado.cerveja.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	
	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString(): "")
				.toString().replaceAll("\\+", "%20");
		this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
	}
	
	public List<T> getConteudo(){
		return page.getContent();
	}

	public boolean isVazia(){
		return page.getContent().isEmpty();
	}
	
	public int getAtual(){
			return page.getNumber();
	}
	
	public boolean isPrimeira(){
		return page.isFirst();
	}
	
	public boolean isUltima(){
		return page.isLast();
	}
	
	public int getTotal(){
		return page.getTotalPages();	
	}
	
	public String urlPagina(int pagina){
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	public String urlOrdenada(String propriedade){
		UriComponentsBuilder uriBuilderOrdenar = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toString());
		
		String valorSort = String.format("%s,%s", propriedade, inveterDirecao(propriedade));
		
		return uriBuilderOrdenar.replaceQueryParam("sort", valorSort).build(true).encode().toString();
		
	}
	public String inveterDirecao(String propriedade){
		String direcao = "asc";
		Order order = page.getSort() !=null ? page.getSort().getOrderFor(propriedade): null;
		if(order != null){
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" :"asc";
		}
		return direcao;
	}
	public boolean descendente(String propriedade){
		return inveterDirecao(propriedade).equals("asc");
	}
	
	public boolean ordenada(String propriedade){
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		return order != null;
		}
	
	
}





