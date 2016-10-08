package cs3219.jms.order;

/**
 * Matric 1:
 * Name   1:
 * 
 * Matric 2:
 * Name   2:
 *
 * This file implements a pipe that transfer messages using JMS.
 */

public class JmsPipe implements IPipe {
    private String jmsFactory;
    private String queue;
    
    public JmsPipe(String jmsFactory, String queue) {
        this.jmsFactory = jmsFactory;
        this.queue = queue;
    }

    @Override
    public void write(Order s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Order read() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }
}
