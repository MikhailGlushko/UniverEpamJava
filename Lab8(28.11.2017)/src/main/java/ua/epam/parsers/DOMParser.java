package ua.epam.parsers;

import ua.epam.tariff.Tariff.Plan;
import ua.epam.tariff.Tariff.Plan.CallPrices;
import ua.epam.tariff.Tariff.Plan.Parameters;
import ua.epam.tariff.Tariff.Plan.Parameters.FavouriteNumber;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * DOM parser for XML
 */
public class DOMParser extends Parser {

    @Override
    public void parse(InputStream inputStream) throws Exception {
        logger.info("DOM start");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element planXML = (Element) node;
                plans.add(parsePlan(planXML));
            }
        }

        System.out.println(tariff);
        logger.info("DOM stop");
    }

    /**
     * Parses plan from XML element
     * @param planXML XML element with plan information
     * @return Plan object parsed from XML
     */
    private Plan parsePlan(Element planXML) {
        logger.info("DOM start to parse Plan");
        Plan plan = new Plan();
        NodeList nodes = planXML.getChildNodes();

        plan.setName(planXML.getAttribute(NAME));
        plan.setPlanID(Integer.valueOf(planXML.getAttribute(PLAN_ID)));

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(OPERATOR)) {
                    plan.setOperatorName(node.getFirstChild().getNodeValue());
                }
                if (node.getNodeName().equals(PAYROLL)) {
                    plan.setPayroll(Integer.valueOf(node.getFirstChild().getNodeValue()));
                }
                if (node.getNodeName().equals(CALL_PRICES)) {
                    plan.setCallPrices(parseCallPrices((Element) node));
                }
                if (node.getNodeName().equals(SMS_PRICE)) {
                    plan.setSmsPrice(Double.valueOf(node.getFirstChild().getNodeValue()));
                }
                if (node.getNodeName().equals(PARAMETERS)) {
                    plan.setParameters(parseParameters((Element) node));
                }
            }
        }

        return plan;
    }

    /**
     * Parses call prices from XML element
     * @param callPricesXML XML element with call prices information
     * @return Call prices object parsed from XML
     */
    private CallPrices parseCallPrices(Element callPricesXML) {
        logger.info("DOM start to parse Prices");
        CallPrices callPrices = new CallPrices();
        NodeList nodes = callPricesXML.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(INSIDE_CALLS)) {
                    callPrices.setInsideNetCalls(Double.valueOf(node.getFirstChild().getNodeValue()));
                }
                if (node.getNodeName().equals(OUTSIDE_CALLS)) {
                    callPrices.setOutsideNetCalls(Double.valueOf(node.getFirstChild().getNodeValue()));
                }
                if (node.getNodeName().equals(STATIONARY_CALLS)) {
                    callPrices.setStationaryPhoneCalls(Double.valueOf(node.getFirstChild().getNodeValue()));
                }
            }
        }

        return callPrices;
    }

    /**
     * Parses parameters from XML element
     * @param parametersXML XML element with parameters information
     * @return Parameters object parsed from XML
     */
    private Parameters parseParameters(Element parametersXML) {
        logger.info("DOM start to parse Parameters");
        Parameters parameters = new Parameters();
        NodeList nodes = parametersXML.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(FAVOURITE_NUMBER)) {
                    parameters.setFavouriteNumber(parseFavouriteNumber((Element) node));
                }
                if (node.getNodeName().equals(BILLING)) {
                    parameters.setBilling(node.getFirstChild().getNodeValue());
                }
                if (node.getNodeName().equals(ACTIVATION_PRICE)) {
                    parameters.setActivationPrice(Integer.valueOf(node.getFirstChild().getNodeValue()));
                }
            }
        }

        return parameters;
    }

    /**
     * Parses favourite number from XML element
     * @param favouriteNumberXML XML element with favourite number information
     * @return Favourite number object parsed from XML
     */
    private FavouriteNumber parseFavouriteNumber(Element favouriteNumberXML) {
        logger.info("DOM start to get Favorite number");
        FavouriteNumber favouriteNumber = new FavouriteNumber();

        favouriteNumber.setValue(Integer.valueOf(favouriteNumberXML.getFirstChild().getNodeValue()));
        favouriteNumber.setNumbersAmount(Integer.valueOf(favouriteNumberXML.getAttribute(NUMBERS_AMOUNT)));

        return favouriteNumber;
    }
}