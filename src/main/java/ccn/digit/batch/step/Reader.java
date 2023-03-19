package ccn.digit.batch.step;

import org.springframework.batch.item.ItemReader;

public class Reader implements ItemReader<String> {
    private String[] message = {"ming", "mingming", "mingmingming"};

    private int count = 0;

    @Override
    public String read() {
        if(count < message.length){
            return message[count++];
        }else{
            count = 0;
        }
        return null;
    }
}
