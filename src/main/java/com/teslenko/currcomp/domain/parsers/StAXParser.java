package com.teslenko.currcomp.domain.parsers;

import com.teslenko.currcomp.domain.exchanges.Exchange;
import com.teslenko.currcomp.domain.rates.Rate;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Most efficient implementation of XMLParser interface.
 * Use event handling to parse the Rates through the xml file.
 * Based on StAX.
 */
public class StAXParser implements XMLParser {
    private final String ITEM = "item";
    private final String FROM = "from";
    private final String TO = "to";
    private final String IN = "in";
    private final String OUT = "out";
    private final String RESERVE = "amount";

    private final XMLInputFactory factory = XMLInputFactory.newFactory();

    @SuppressWarnings({"ConstantConditions"})
    public List<Rate> readRatesFromXML(Exchange exchange) throws XMLParsingException {
        List<Rate> items = new LinkedList<>();
        Rate item = null;

        try {
            URL url = new URL(exchange.getRatesURL());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getContentType().contains("xml")) {
                InputStream in = connection.getInputStream();
                XMLEventReader reader = factory.createXMLEventReader(in);

                while (reader.hasNext()) {
                    XMLEvent event = reader.nextEvent();

                    if (event.isStartElement()) {
                        StartElement startElement = event.asStartElement();
                        switch (startElement.getName().getLocalPart()) {
                            case ITEM:
                                item = new Rate();
                                item.setExchange(exchange);
                                break;
                            case FROM:
                                event = reader.nextEvent();
                                item.setFrom(event.asCharacters().getData());
                                break;
                            case TO:
                                event = reader.nextEvent();
                                item.setTo(event.asCharacters().getData());
                                break;
                            case IN:
                                event = reader.nextEvent();
                                item.setIn(event.asCharacters().getData());
                                break;
                            case OUT:
                                event = reader.nextEvent();
                                item.setOut(event.asCharacters().getData());
                                break;
                            case RESERVE:
                                event = reader.nextEvent();
                                item.setAmount(event.asCharacters().getData());
                                break;
                        }
                    }

                    if (event.isEndElement()) {
                        if (ITEM.equals(event.asEndElement().getName().getLocalPart())) {
                            if (item != null) {
                                items.add(item);
                            }
                        }
                    }
                }
                reader.close();
                in.close();
            } else {
                throw new XMLParsingException(exchange.getRatesURL().toUpperCase() + " has wrong (not xml) format");
            }
        } catch(XMLStreamException | IOException e){
            throw new XMLParsingException(exchange.getRatesURL().toUpperCase() + " parsing by StAX is failed.", e);
        }
        return items;
    }
}
