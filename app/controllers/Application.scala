
package controllers

import play.api._
import play.api.mvc._
import java.net._
import scala.collection.JavaConverters._
import java.net.NetworkInterface
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  def index = Action {

    implicit request =>
    val address =  request.remoteAddress
   var macAddress = Array.empty[Byte]

    Logger.info("address " + address)
    val Address1 = InetAddress.getLocalHost()
    val localNetworkInterface = NetworkInterface.getNetworkInterfaces
    while (localNetworkInterface.hasMoreElements) {
  val element = localNetworkInterface.nextElement
  if (element.getDisplayName.equalsIgnoreCase("eth0")) {
    macAddress = element.getHardwareAddress
    println(macAddress)
  }
}
    val json = ipInfo(address, " ")

  Ok("Got request [" + address + Address1+ "]")
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

val userForm = Form(
  tuple(
    "ip" -> text,
    "sub" -> text
  )
)
 def getIp() = Action {
     implicit request =>
     val (ip, sub) = userForm.bindFromRequest.get
     Ok("Got request ["+ip+"]")
 }
 
 

}