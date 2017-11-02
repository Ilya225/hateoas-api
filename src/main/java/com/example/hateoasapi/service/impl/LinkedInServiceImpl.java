package com.example.hateoasapi.service.impl;

import com.example.hateoasapi.service.LinkedInService;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.springframework.stereotype.Service;
import com.gargoylesoftware.htmlunit.CookieManager;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class LinkedInServiceImpl implements LinkedInService {

    public Map login(String email, String password) {
        Map<String, String> result = new HashMap<>();
        WebClient webClient = new WebClient();
        //Ignore js exceptions
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36");

        try {
            HtmlPage page = webClient.getPage("https://www.linkedin.com/uas/login");

            HtmlForm loginForm = page.getFormByName("login");
            HtmlTextInput emailField = loginForm.getInputByName("session_key");
            HtmlPasswordInput passwordField = loginForm.getInputByName("session_password");
            HtmlSubmitInput submitBtn = loginForm.getInputByName("signin");

            emailField.setValueAttribute(email);
            passwordField.setValueAttribute(password);

//            page = (HtmlPage) passwordField.type(13);
//            page = (HtmlPage) page.executeJavaScript("document.getElementById('btn-primary').click()").getNewPage();
//            page = (HtmlPage) loginForm.fireEvent(Event.TYPE_SUBMIT).getNewPage();
            page = submitBtn.click();

            webClient.waitForBackgroundJavaScript(500);

            Set<Cookie> cookiesSet = webClient.getCookieManager().getCookies();

            result.put("cookies", cookiesSet.toString());
            result.put("newUrl", page.getUrl().toString());
            result.put("newPage", page.getWebResponse().getContentAsString());

        } catch(IOException ex){
            result.put("result", "IOException");
        }

        return result;
    }

    public Map login2(String email, String password) {
        Map<String, String> result = new HashMap<>();
        WebClient webClient = new WebClient();
        //Ignore js exceptions
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36");

        try {
            webClient.getPage("https://www.linkedin.com");

            URL url = new URL("https://www.linkedin.com/uas/login-submit");
            WebRequest requestSettings = new WebRequest(url, HttpMethod.POST);

            requestSettings.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            requestSettings.setAdditionalHeader("Cache-Control", "no-cache");
            requestSettings.setAdditionalHeader("Pragma", "no-cache");
            requestSettings.setAdditionalHeader("Origin", "https://www.linkedin.com");

            requestSettings.setRequestBody("session_key=" + email + "&session_password=" + password);

            Page page = webClient.getPage(requestSettings);
            webClient.waitForBackgroundJavaScript(500);

            Set<Cookie> cookiesSet = webClient.getCookieManager().getCookies();

            result.put("cookies", cookiesSet.toString());
            result.put("newUrl", page.getUrl().toString());
            result.put("newPage", page.getWebResponse().getContentAsString());

        } catch(Exception ex){
            result.put("result", "IOException");
        }

        return result;
    }
}