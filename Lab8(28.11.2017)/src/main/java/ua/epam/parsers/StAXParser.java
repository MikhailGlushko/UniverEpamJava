package ua.epam.parsers;

import ua.epam.tariff.Tariff.Plan;

import java.io.InputStream;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * StAX Parser for XML
 */
public class StAXParser extends Parser {

    /** Last created plan object */
    private Plan plan = null;

    @Override
    public void parse(InputStream inputStream) throws Exception {
        logger.info("StAX start");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(inputStream);

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    startElementCase(event);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    charactersCase(event);
                    break;
            }
        }

        System.out.println(tariff);
        logger.info("StAX end");
    }

    private void charactersCase(XMLEvent event) {
        Characters characters = event.asCharacters();

        if (state == State.OPERATOR) {
            plan.setOperatorName(characters.getData());
        } else if (state == State.PAYROLL) {
            plan.setPayroll(Double.valueOf(characters.getData()));
        } else if (state == State.INSIDE_CALLS) {
            plan.getCallPrices().setInsideNetCalls(Double.valueOf(characters.getData()));
        } else if (state == State.OUTSIDE_CALLS) {
            plan.getCallPrices().setOutsideNetCalls(Double.valueOf(characters.getData()));
        } else if (state == State.STATIONARY_CALLS) {
            plan.getCallPrices().setStationaryPhoneCalls(Double.valueOf(characters.getData()));
        } else if (state == State.SMS_PRICE) {
            plan.setSmsPrice(Double.valueOf(characters.getData()));
        } else if (state == State.FAVOURITE_NUMBER) {
            plan.getParameters().getFavouriteNumber().setValue(Integer.valueOf(characters.getData()));
        } else if (state == State.BILLING) {
            plan.getParameters().setBilling(characters.getData());
        } else if (state == State.ACTIVATION_PRICE) {
            plan.getParameters().setActivationPrice(Double.valueOf(characters.getData()));
        }

        state = State.NONE;
    }

    private void startElementCase(XMLEvent event) {
        StartElement startElement = event.asStartElement();
        String qName = startElement.getName().getLocalPart();

        switch (qName) {
            case PLAN:
                parsePlan(startElement);
                break;
            case OPERATOR:
                state = State.OPERATOR;
                break;
            case PAYROLL:
                state = State.PAYROLL;
                break;
            case INSIDE_CALLS:
                state = State.INSIDE_CALLS;
                break;
            case OUTSIDE_CALLS:
                state = State.OUTSIDE_CALLS;
                break;
            case STATIONARY_CALLS:
                state = State.STATIONARY_CALLS;
                break;
            case SMS_PRICE:
                state = State.SMS_PRICE;
                break;
            case FAVOURITE_NUMBER:
                state = State.FAVOURITE_NUMBER;
                parseFavouriteNumber(startElement);
                break;
            case BILLING:
                state = State.BILLING;
                break;
            case ACTIVATION_PRICE:
                state = State.ACTIVATION_PRICE;
                break;
        }
    }

    private void parsePlan(StartElement startElement) {
        logger.info("StAX parse Plan");
        plan = new Plan();

        Iterator<Attribute> attributes = startElement.getAttributes();

        plan.setName(attributes.next().getValue());
        plan.setPlanID(Integer.valueOf(attributes.next().getValue()));
        plans.add(plan);
    }

    private void parseFavouriteNumber(StartElement startElement) {
        logger.info("StAX parse Favorite number");
        Iterator<Attribute> attributes = startElement.getAttributes();
        plan.getParameters().getFavouriteNumber().setNumbersAmount(Integer.valueOf(attributes.next().getValue()));
    }
}