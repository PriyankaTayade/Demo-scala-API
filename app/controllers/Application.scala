package controllers

import play.api._
import play.api.mvc._
import java.net._


object Application extends Controller {

  def index = Action {
 
    implicit request =>
    val address =  request.remoteAddress
    Logger.info("address " + address)
    val json = ipInfo(address, "")
  Ok("Got request [" + address +json+ "]")
  }
  
  def ipInfo(ip: String, key: String) = {
      
    val url = new java.net.URL("http://api.ipinfodb.com/v3/ip-city/?key=" + 
            key + "&ip=" + ip + "&format=json")
    val connection = url.openConnection
    val inputStream = connection.getInputStream
    val location = new String(Stream.continually(inputStream.read)
            .takeWhile(-1 !=).map(_.toByte).toArray)
    inputStream.close()
    location
}

}