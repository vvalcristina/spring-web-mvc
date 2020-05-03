package br.com.digitalinnovationone.springwebmvc.controller;

import br.com.digitalinnovationone.springwebmvc.model.Jedi;
import br.com.digitalinnovationone.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

//Intercepta as requisões para da View para o BackEnd
@Controller
public class JediController {

    //Injeção de dependências
    @Autowired
    private JediRepository repository;

    //Acesso a lista de Jedis
    @GetMapping("/jedi")
    public ModelAndView jedi(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        modelAndView.addObject("allJedi", repository.getAllJedi());
        return modelAndView;
    }

    //Cadastrar novo Jedi
    @GetMapping("/new-jedi")
    public ModelAndView newJedi(){
        final ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("new-jedi");
        modelAndView.addObject("jedi", new Jedi());
        return modelAndView;
    }

    //Submetendo o cadastro de Jedi
    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirectAttributes){

        //Se houver erros a requisição continua na pagina de cadastro
        if (result.hasErrors()){
            return "new-jedi";
        }

        repository.add(jedi);
        redirectAttributes.addFlashAttribute("message", "Sucess!");
        return "redirect:jedi";
    }


}
