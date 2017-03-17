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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carledwin.ti.contabil.model.StatusTitulo;
import com.carledwin.ti.contabil.model.Titulo;
import com.carledwin.ti.contabil.repository.filter.TituloFilter;
import com.carledwin.ti.contabil.service.CadastroTituloService;

@Controller
@RequestMapping(TituloController.URL_TITULOS)
public class TituloController {
	
	private static final String CADASTRO_TITULO_VIEW = "CadastroTitulo";
	private static final String MSG_TITULO_SALVO_COM_SUCESSO = "Título salvo com sucesso!";
	private static final String MSG_TITULO_EXCLUIDO_COM_SUCESSO = "Título excluido com sucesso!";
	private static final String PESQUISA_TITULOS_VIEW = "PesquisaTitulos";
	private static final String REDIRECT_TITULOS = "redirect:/titulos";
	private static final String REDIRECT_TITULOS_NOVO = "redirect:/titulos/novo";
	private static final String URL_NOVO = "/novo";
	public static final String URL_TITULOS = "/titulos";
	private static final String VAR_MENSAGEM = "mensagem";
	private static final String VAR_TITULOS = "titulos";
	
	@Autowired
	private CadastroTituloService cadastroTitulosService;
	
	@RequestMapping
	public ModelAndView pesquisas(@ModelAttribute("filter") TituloFilter filter){
		ModelAndView mv = new ModelAndView(PESQUISA_TITULOS_VIEW);
		
		mv.addObject(VAR_TITULOS, cadastroTitulosService.findByFilter(filter));
		return mv;
	}
	
	@RequestMapping(value="/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo){
		return cadastroTitulosService.receber(codigo);
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes){
		cadastroTitulosService.delete(codigo);
		attributes.addFlashAttribute(VAR_MENSAGEM, MSG_TITULO_EXCLUIDO_COM_SUCESSO);
		return REDIRECT_TITULOS;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Titulo titulo){
		ModelAndView mv = new ModelAndView(CADASTRO_TITULO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes){
		if(errors.hasErrors()){
			return CADASTRO_TITULO_VIEW;
		}
		try{
			cadastroTitulosService.save(titulo);
			attributes.addFlashAttribute(VAR_MENSAGEM,MSG_TITULO_SALVO_COM_SUCESSO);
			return REDIRECT_TITULOS;
		}catch(IllegalArgumentException e){
			errors.reject("dataVencimento", null, e.getMessage());
			return CADASTRO_TITULO_VIEW;
		}
	}

	@RequestMapping(URL_NOVO)
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_TITULO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@ModelAttribute
	public List<StatusTitulo> statusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
}
