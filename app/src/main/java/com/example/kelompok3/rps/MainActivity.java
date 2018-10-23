package com.example.kelompok3.rps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    ListView ls ;

  ArrayList<HashMap<String, String>> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView1);
        ls = findViewById(R.id.ls);
        try {
            InputStream is = getAssets().open("file.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("employee");

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    HashMap<String,String> user = new HashMap<>();
//                    tv1.setText(tv1.getText()+"\nName : " + getValue("name", element2)+"\n");
//                    tv1.setText(tv1.getText()+"Surname : " + getValue("surname", element2)+"\n");
//                    tv1.setText(tv1.getText()+"-----------------------");
                    user.put("name",getValue("name",element2));
                    user.put("surname",getValue("surname",element2));
                    userList.add(user);


                }
            }
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,userList,R.layout.item,new String[]{"name","surname"}, new int[]{R.id.tv_list,R.id.tv_list2});
            ls.setAdapter(adapter);

        } catch (Exception e) {e.printStackTrace();}

    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
    }

