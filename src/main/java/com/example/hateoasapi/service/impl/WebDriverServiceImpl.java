package com.example.hateoasapi.service.impl;

import com.example.hateoasapi.service.WebDriverService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebDriverServiceImpl implements WebDriverService {
    public List<String> getSearchResults(String query) {
        List<String> result = new ArrayList<>();
        WebClient webClient = new WebClient();
        //Ignore js exceptions
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        //The most useless exception handling in the world
        try {
            //connecting to Google
            HtmlPage page = webClient.getPage("http://www.google.com/");

            //Get page elements
            HtmlForm searchForm = page.getFormByName("f");
            HtmlTextInput searchField = searchForm.getInputByName("q");
            HtmlInput button = searchForm.getInputByName("btnK");

            //Type search query
            searchField.setValueAttribute(query);
            //Click "Search" and get new page
            page = button.click();
            //Wait a while, just in case
            webClient.waitForBackgroundJavaScript(500);

            //Parse results urls
            List<HtmlElement> urlCites = page.getByXPath("//cite[@class='_Rm']");
            List<String> urls = new ArrayList<>();
            for (HtmlElement cite : urlCites) {
                result.add(cite.getTextContent());
            }

        } catch(IOException ex){
            result.add("Error");
        }

        return result;
    }
}