//package br.com.guiabolso.centraldeerros.controller;
//
//import br.com.guiabolso.centraldeerros.entity.Account;
//import br.com.guiabolso.centraldeerros.service.AccountService;
//import br.com.guiabolso.centraldeerros.validation.AccountValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/centralerros/account")
//public class AccountController {
//
//    @Autowired
//    AccountService accountService;
//
//    @Autowired
//    private AccountValidator accountValidator;
//
//    @PostMapping
//    public ResponseEntity<Account> saveAccount(@Valid @RequestBody Account account){
//        try{
//            this.accountService.saveAccount(account);
//            return ResponseEntity.status(HttpStatus.CREATED).body(account);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/registration")
//    public String register(Model model)
//    {
//    	model.addAttribute("accountForm", new Account());
//    	return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String register(@ModelAttribute("accountForm") Account accountForm, BindingResult bindingResult)
//    {
//    	accountValidator.validate(accountForm, bindingResult);
//    	if (bindingResult.hasErrors())
//    		return "registration";
//
//    	accountService.save(accountForm);
//    	accountService.doLogin(accountForm.getUsername(), accountForm.getPassword());
//
//    	return "redirect:/welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Usuario e senha invalidos.");
//
//        if (logout != null)
//            model.addAttribute("message", "Voce foi deslogado com sucesso.");
//
//        return "login";
//    }
//
//}
//
