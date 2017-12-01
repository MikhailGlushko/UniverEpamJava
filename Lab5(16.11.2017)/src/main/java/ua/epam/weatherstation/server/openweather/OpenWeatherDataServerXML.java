package ua.epam.weatherstation.server.openweather;

import ua.epam.weatherstation.dao.WeatherDataStorage;
import ua.epam.weatherstation.entity.Pair;
import ua.epam.weatherstation.entity.Weather;
import ua.epam.weatherstation.server.DataServer;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.Iterator;

/**
 * реалізація доступу до конкретного погоднього сервера http://openweathermap.org та опрацювання отриманих даних
 */

public class OpenWeatherDataServerXML extends DataServer {
    public final String MODE   = "xml";

    public OpenWeatherDataServerXML(WeatherDataStorage weatherData) {
        super(weatherData);
    }

    /**
     * Обробка отриманих даних
     * @return
     */
    protected Weather weatherParse(){
        Pair temperature  = new Pair();
        Pair humidity  = new Pair();
        Pair pressure  = new Pair();

        try{
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(getDataProvider().getInputStream());
            while (reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if(qName.equalsIgnoreCase("temperature")){
                            temperature = extractData(temperature, startElement);
                        } else if (qName.equalsIgnoreCase("humidity")){
                            humidity = extractData(humidity, startElement);
                        } else if (qName.equalsIgnoreCase("pressure")){
                            pressure = extractData(pressure, startElement);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        break;
                }
            }
        } catch (Exception e){
        }
        return new Weather(temperature, humidity, pressure);
    }

    /**
     * Отримання даних
     * @param pair
     * @param startElement
     */
    protected Pair extractData(Pair pair, StartElement startElement) {
        Iterator<Attribute> attributes = startElement.getAttributes();
        while (attributes.hasNext()){
            Attribute attr = attributes.next();
            if(attr.getName().toString().equals("value")) {
                pair.setValue(attr.getValue().toString());
            } else if(attr.getName().toString().equals("unit")) {
                pair.setUnit(attr.getValue().toString());
            }
        }
        return pair;
    }
}
