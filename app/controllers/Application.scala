package controllers

import play.api._
import play.api.mvc._
import java.net._


object Application extends Controller {

  def index = Action {
 
    implicit request =>
    val address =  request.remoteAddress
    Logger.info("address " + address)
  Ok("Got request [" + address + "]")
  }

}