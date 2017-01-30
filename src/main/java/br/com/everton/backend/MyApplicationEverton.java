/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.everton.backend;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Everton
 */
@ApplicationPath("rest")
public class MyApplicationEverton extends ResourceConfig {

    public MyApplicationEverton() {
        packages("io.github.dggodoi.aprendendo.java.backend.controllers");
    }
}
