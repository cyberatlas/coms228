package edu.iastate.cs228.hw4;

public class Tester {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <V, K> void main(String[] args)
    {
        // TODO Auto-generated method stub
        EntryTree test = new EntryTree();
        Object[] key = new Object[]{'e','d','i','t'};
        String value = "change";
        Object[] key2 = new Object[]{'e','d','i','t'};
        String value2 = "revise";
        Object[] key3 = new Object[]{'e','d','i','t','e','d'};
        String value3 = "revised";
        Object[] key4 = new Object[]{'e','d','j'};
        String value4 = "thing";

        test.showTree();

        test.add(key, value);
        test.showTree();

        test.add(key2, value2);
        test.showTree();

        test.add(key3, value3);
        test.showTree();

		test.add(key4, value4);
	   	test.showTree();
    }

}
