package com.example.cliente_webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;


public class WebService {

	private static String NAMESPACE = "http://webservice/";

	public static String saudacao(String nome, String imei) {
		String URL = pegarServidor();

		// Create request
		SoapObject request = new SoapObject(NAMESPACE, "saudacao");

		PropertyInfo nomep = new PropertyInfo();
		// Set Name
		nomep.setName("nome");
		// Set Value
		nomep.setValue(nome);
		// Set dataType
		nomep.setType(String.class);
		// Add the property to request object
		request.addProperty(nomep);

		PropertyInfo imeip = new PropertyInfo();
		// Set Name
		imeip.setName("imei");
		// Set Value
		imeip.setValue(imei);
		// Set dataType
		imeip.setType(String.class);
		// Add the property to request object
		request.addProperty(imeip);

		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		new MarshalBase64().register(envelope);
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

		try {
			androidHttpTransport.getServiceConnection().setRequestProperty(
					"Connection", "close");
			System.setProperty("http.keepAlive", "false");
			// Invoke web service
			androidHttpTransport.call(NAMESPACE+"saudacao/",
					envelope);
			// Get the response
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			// Assign it to resTxt variable static variable
			return response.toString();

		} catch (Exception e) {
			// Print error
			e.printStackTrace();
			Log.i("erro", "" + e);
			// Assign error message to resTxt
			return "Erro de Comunicao";
		}
		// Return resTxt to calling object

	}

	public static String pegarServidor() {

		return "http://" + MainActivity.webservice
				+ "/webservice/WebService1?wsdl";
	}
}