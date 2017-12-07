package ua.epam.parsers;

import org.apache.log4j.Logger;
import ua.epam.tariff.ObjectFactory;
import ua.epam.tariff.Tariff;

import java.io.InputStream;
import java.util.List;

/**
 * Parser interface
 */
public abstract class Parser {

    static Logger logger = Logger.getLogger(Parser.class);

    protected enum State {NONE, OPERATOR, PAYROLL, INSIDE_CALLS, OUTSIDE_CALLS, STATIONARY_CALLS, SMS_PRICE,
                            FAVOURITE_NUMBER, BILLING, ACTIVATION_PRICE}

    protected static final String PLAN = "plan";
    protected static final String NAME = "name";
    protected static final String PLAN_ID = "planID";
    protected static final String OPERATOR = "operator-name";
    protected static final String PAYROLL = "payroll";
    protected static final String CALL_PRICES = "call-prices";
    protected static final String SMS_PRICE = "sms-price";
    protected static final String PARAMETERS = "parameters";
    protected static final String INSIDE_CALLS = "inside-net-calls";
    protected static final String OUTSIDE_CALLS = "outside-net-calls";
    protected static final String STATIONARY_CALLS = "stationary-phone-calls";
    protected static final String FAVOURITE_NUMBER = "favourite-number";
    protected static final String BILLING = "billing";
    protected static final String ACTIVATION_PRICE = "activation-price";
    protected static final String NUMBERS_AMOUNT = "numbers-amount";

    /** Tariff object */
    protected Tariff tariff = new ObjectFactory().createTariff();

    /** List of tariff plans */
    protected List<Tariff.Plan> plans = tariff.getPlan();

    /** State of parser */
    protected State state = State.NONE;

    /**
     * Parse method
     * @param inputStream Input stream to read from
     * @throws Exception
     */
    public abstract void parse(InputStream inputStream) throws Exception;
}