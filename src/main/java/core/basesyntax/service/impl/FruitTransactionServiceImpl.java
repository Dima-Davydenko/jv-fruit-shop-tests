package core.basesyntax.service.impl;

import core.basesyntax.exceptions.NumberOfColumnsException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitTransactionService;
import java.util.List;
import java.util.stream.Collectors;

public class FruitTransactionServiceImpl implements FruitTransactionService {
    private static final int TITLE_LINE_INDEX = 0;
    private static final int OPERATION_INDEX = 0;
    private static final int FRUIT_NAME_INDEX = 1;
    private static final int FRUIT_QUANTITY_INDEX = 2;

    @Override
    public List<FruitTransaction> get(List<String> data) {
        if (data == null) {
            throw new RuntimeException("Data can't be null");
        }
        data.remove(TITLE_LINE_INDEX);
        return data.stream()
                .map(this::getTransaction)
                .collect(Collectors.toList());
    }

    private FruitTransaction getTransaction(String lineFromFile) {
        String[] transaction = lineFromFile.split(",");
        FruitTransaction fruitTransaction = new FruitTransaction();
        try {
            fruitTransaction.setOperation(fruitTransaction
                    .getOperationByLetter(transaction[OPERATION_INDEX]));
            fruitTransaction.setFruit(transaction[FRUIT_NAME_INDEX]);
            fruitTransaction.setQuantity(Integer.parseInt(transaction[FRUIT_QUANTITY_INDEX]));
        } catch (IndexOutOfBoundsException e) {
            throw new NumberOfColumnsException("Invalid number of columns in file line - "
                    + lineFromFile, e);
        }
        return fruitTransaction;
    }
}