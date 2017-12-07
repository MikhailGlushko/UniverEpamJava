package ua.epam.parsers;

import ua.epam.tariff.Tariff;
import ua.epam.tariff.Tariff.Plan;

import java.io.InputStream;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * SAX Parser for XML
 */
public class SAXParser extends Parser {

    /** Last created plan object */
    private Plan plan = null;

    /**
     * SAX Handler class for parsing
     */
    private class SAXHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            switch (qName) {
                case PLAN:
                    plan = new Plan();
                    plan.setName(attributes.getValue(NAME));
                    plan.setPlanID(Integer.valueOf(attributes.getValue(PLAN_ID)));
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
                    plan.getParameters().getFavouriteNumber().setNumbersAmount(Integer.valueOf
                            (attributes.getValue(NUMBERS_AMOUNT)));
                    break;
                case BILLING:
                    state = State.BILLING;
                    break;
                case ACTIVATION_PRICE:
                    state = State.ACTIVATION_PRICE;
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (localName.equals(PLAN)) {
                plans.add(plan);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (state == State.OPERATOR) {
                plan.setOperatorName(new String(ch, start, length));
            } else if (state == State.PAYROLL) {
                plan.setPayroll(Double.valueOf(new String(ch, start, length)));
            } else if (state == State.INSIDE_CALLS) {
                plan.getCallPrices().setInsideNetCalls(Double.valueOf(new String(ch, start, length)));
            } else if (state == State.OUTSIDE_CALLS) {
                plan.getCallPrices().setOutsideNetCalls(Double.valueOf(new String(ch, start, length)));
            } else if (state == State.STATIONARY_CALLS) {
                plan.getCallPrices().setStationaryPhoneCalls(Double.valueOf(new String(ch, start, length)));
            } else if (state == State.SMS_PRICE) {
                plan.setSmsPrice(Double.valueOf(new String(ch, start, length)));
            } else if (state == State.FAVOURITE_NUMBER) {
                plan.getParameters().getFavouriteNumber().setValue(Integer.valueOf(new String(ch, start, length)));
            } else if (state == State.BILLING) {
                plan.getParameters().setBilling(new String(ch, start, length));
            } else if (state == State.ACTIVATION_PRICE) {
                plan.getParameters().setActivationPrice(Double.valueOf(new String(ch, start, length)));
            }

            state = State.NONE;
        }
    }

    @Override
    public void parse(InputStream inputStream) throws Exception {
        logger.info("SAX start");
        XMLReader reader = XMLReaderFactory.createXMLReader();
        SAXHandler contentHandler = new SAXHandler();

        reader.setContentHandler(contentHandler);
        reader.parse(new InputSource(inputStream));
        System.out.println(tariff);
        logger.info("SAX end");
    }
}