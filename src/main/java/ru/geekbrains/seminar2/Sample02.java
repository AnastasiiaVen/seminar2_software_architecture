package ru.geekbrains.seminar2;

public class Sample02 {

    public static void main(String[] args) {

        /*LogReader logReader1 = new OperationSystemLogEventReader();
        logReader1.setCurrentPosition(10);
        logReader1.readLogEntry();
        LogReader logReader2 = new TextFileReader();
        logReader2.setCurrentPosition(10);
        logReader2.readLogEntry();
        LogReader logReader3 = new DatabaseReader();
        logReader3.setCurrentPosition(10);
        logReader3.readLogEntry();*/

        ConcreteReaderCreator concreteReaderCreator = new ConcreteReaderCreator();
        LogReader logreader = concreteReaderCreator.createLogReader(LogType.Poem);
        logreader.setDatasource(Sample01.data);
        logreader.readLogEntry();


        for (LogEntry log: logreader.readLogEntry()) {
            System.out.println(log.getText());
        }
    }

}

enum LogType{
    Text,
    Poem,
    Database,
    System
}

abstract class BaseLogReaderCreator{

    public LogReader createLogReader(LogType logType){

        LogReader logReader = createLogReaderInstance(logType);
        //TODO: Исполним базовые/подготовительные алгоритмы ...
        logReader.setCurrentPosition(20);

        return logReader;
    }

    /**
     * Фабричный метод, может создавать экземпляры типа LogReader
     * @return
     */
    protected abstract LogReader createLogReaderInstance(LogType logType);

}

class ConcreteReaderCreator extends BaseLogReaderCreator{

    @Override
    protected LogReader createLogReaderInstance(LogType logType) {
        return switch (logType){
            case Poem -> new PoemReader();
            case Text -> new TextFileReader();
            case Database ->  new DatabaseReader();
            case System ->  new OperationSystemLogEventReader();
        };
    }
}




class TextFileReader extends LogReader {

    @Override
    public void setDatasource(Object data) {

    }

    @Override
    public Object getDatasource() {
        return null;
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        return null;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return null;
    }
}


class DatabaseReader extends LogReader{

    @Override
    public void setDatasource(Object data) {

    }

    @Override
    public Object getDatasource() {
        return null;
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        return null;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return null;
    }
}

class OperationSystemLogEventReader extends LogReader{

    @Override
    public void setDatasource(Object data) {

    }

    @Override
    public Object getDatasource() {
        return null;
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        return null;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return null;
    }
}











