package com.carledwin.ti.contabil.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carledwin.ti.contabil.model.Despesa;
import com.carledwin.ti.contabil.model.StatusDespesaEnum;
import com.carledwin.ti.contabil.model.TipoDespesaEnum;
import com.carledwin.ti.contabil.repository.filter.DespesaFilter;
import com.carledwin.ti.contabil.service.DespesaService;

@Controller
@RequestMapping(DespesaController.URL_DESPESAS)
public class DespesaController {
	
	private static final String CADASTRO_VIEW = "CadastroDespesa";
	private static final String MSG_CADASTRO_SUCESSO = "Despesa salva com sucesso!";
	private static final String MSG_EXCLUSAO_SUCESSO = "Despesa excluida com sucesso!";
	private static final String PESQUISA_VIEW = "PesquisaDespesa";
	private static final String REDIRECT_DESPESAS= "redirect:/despesas";
	private static final String URL_NOVA = "/nova";
	public static final String URL_DESPESAS = "/despesas";
	private static final String VAR_MENSAGEM = "mensagem";
	private static final String VAR_DESPESAS = "despesas";
	
	@Autowired
	private DespesaService service;
	
	@RequestMapping
	public ModelAndView pesquisas(@ModelAttribute("filter") DespesaFilter filter){
		ModelAndView mv = new ModelAndView(PESQUISA_VIEW);
		
		mv.addObject(VAR_DESPESAS, service.findByFilter(filter));
		return mv;
	}
	
	@RequestMapping(value="/{id}/pagar", method = RequestMethod.PUT)
	public @ResponseBody String pagar(@PathVariable Long id){
		return service.pagar(id);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes){
		service.delete(id);
		attributes.addFlashAttribute(VAR_MENSAGEM, MSG_EXCLUSAO_SUCESSO);
		return REDIRECT_DESPESAS;
	}
	
	@RequestMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Despesa despesa){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(despesa);
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String salvar(@Validated Despesa despesa, Errors errors, RedirectAttributes attributes){
		if(errors.hasErrors()){
			return CADASTRO_VIEW;
		}
		try{
			service.save(despesa);
			attributes.addFlashAttribute(VAR_MENSAGEM,MSG_CADASTRO_SUCESSO);
			return REDIRECT_DESPESAS;
		}catch(IllegalArgumentException e){
			errors.reject("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping(URL_NOVA)
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Despesa());
		return mv;
	}
	
	@ModelAttribute
	public List<StatusDespesaEnum> statusDespesa(){
		return Arrays.asList(StatusDespesaEnum.values());
	}
	
	@ModelAttribute
	public List<TipoDespesaEnum> tipoDespesa(){
		return Arrays.asList(TipoDespesaEnum.values());
	}
}
