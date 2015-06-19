package be.ehb.dtsid_inapp.Models;

import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;

/**
 * Created by Kristof on 18/06/2015.
 */

public class XmlHandler extends DefaultHandler {

    public static ArrayList<Gemeente> gemeenteArrayList;

    private Gemeente tempGemeente;
    private StringBuilder stringBuilder;
    DatabaseContract dbc;

    public ArrayList<Gemeente> getGemeenteArrayList() {
        return gemeenteArrayList;
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        gemeenteArrayList = new ArrayList<>();
        stringBuilder = new StringBuilder();

    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (localName.equals("record")) {
            tempGemeente = new Gemeente();;
        }
        if (localName.equalsIgnoreCase("Postcode")){
            stringBuilder = new StringBuilder();
        }
        if (localName.equalsIgnoreCase("Plaatsnaam")){
            stringBuilder = new StringBuilder();
        }
        if (localName.equalsIgnoreCase("Provincie")){
            stringBuilder = new StringBuilder();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);

        stringBuilder.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(uri, localName, qName);

        if (localName.equalsIgnoreCase("Postcode")) {
            tempGemeente.setZip(stringBuilder.toString());

        }
        if (localName.equalsIgnoreCase("Plaatsnaam")) {
            tempGemeente.setPlaats(stringBuilder.toString());

        }
        if (localName.equalsIgnoreCase("Provincie")) {
            tempGemeente.setProvincie(stringBuilder.toString());

        }
        if (localName.equalsIgnoreCase("record"))
        {
            //dbc.createGemeente(tempGemeente);
            gemeenteArrayList.add(tempGemeente);
            Log.d("Test_1", tempGemeente.toString());
            Log.d("Test_2", gemeenteArrayList.size() +"");
        }


    }
    public void createDBC(Context context)
    {
        dbc = new DatabaseContract(context);
    }
}