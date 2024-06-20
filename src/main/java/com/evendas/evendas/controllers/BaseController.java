package com.evendas.evendas.controllers;

import com.evendas.evendas.utils.models.ResponseObject;
import com.evendas.evendas.utils.models.ResponseOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public abstract class BaseController {
//    @Autowired
//    private HttpSession session;

//    public UsuarioLogado getUsuarioLogado() throws Exception {
//        try {
//            return (UsuarioLogado) SessionManager.getData(session, SessionKeys.USUARIO_LOGADO);
//        } catch (Exception ex) {
//            throw new Exception(ex.getMessage());
//        }
//    }

    public ModelAndView redirectError(Exception ex) {
        String[] partsMessage = ex.getMessage().split(": ", 2);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/erro");
        ModelAndView modelAndView = new ModelAndView(redirectView);
        modelAndView.addObject("message", partsMessage[1]);
        return modelAndView;
    }

    public ResponseEntity<?> responseOk(String message) {
        return new ResponseEntity<>(new ResponseOperation(true, message), HttpStatus.OK);
    }

    public ResponseEntity<?> responseOk(ResponseObject object) {
        return new ResponseEntity<>(new ResponseOperation(true, object), HttpStatus.OK);
    }

    public ResponseEntity<?> responseError(Exception ex) {
        return new ResponseEntity<>(new ResponseOperation(ex), HttpStatus.OK);
    }
}
