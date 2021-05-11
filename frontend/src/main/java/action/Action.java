package action;


import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emilienmarion
 */
public abstract class Action {
 
  public abstract void executer(HttpServletRequest request);
 
  }
