package engine.definition.value.random.impl.string;

import engine.definition.value.random.api.AbstractRandomValueGenerator;

public class RandomStringValueGenerator extends AbstractRandomValueGenerator<String> {
    @Override
    public String generateValue() {
        int randomLength=1+random.nextInt(50);
        String characterSet="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?,_-.() ";
        StringBuilder randomString=new StringBuilder();
        int charInd;
        int characterSetLength=characterSet.length();
        for(int i=0;i<randomLength;i++)
        {
            charInd=random.nextInt(characterSetLength);
            randomString.append(characterSet.charAt(charInd));
        }

        return randomString.toString();
    }
}

