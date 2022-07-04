package com.plazavea.webservice.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.plazavea.webservice.security.model.ConfirmationToken;
import com.plazavea.webservice.security.model.Usuario;
import com.plazavea.webservice.security.repository.ConfirmationTokenRepository;
import com.plazavea.webservice.security.service.UsuarioServ;

@Controller
public class UserController {

    @Autowired
    private UsuarioServ usuarioServ;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            Usuario user = usuarioServ.getByEmail(token.getUser().getEmail()).get();
            user.setActivo(true);
            usuarioServ.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
    
}
