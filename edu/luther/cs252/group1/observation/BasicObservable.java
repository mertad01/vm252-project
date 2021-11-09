package edu.luther.cs252.group1.observation;

import java.util.ArrayList;

public class BasicObservable implements Observable {

    private ArrayList< Observer > myObservers;

    public BasicObservable()
    {

        myObservers = new ArrayList< Observer >();

    }

    @Override
    public void attach(Observer anotherObserver)
    {

        if (anotherObserver != null && ! myObservers.contains(anotherObserver))
            myObservers.add(anotherObserver);

    }

    @Override
    public void detach(Observer currentObserver)
    {

        myObservers.remove(currentObserver);

    }

    @Override
    public void announceChange()
    {

        for (Observer currentObserver : myObservers)
            currentObserver.update();

    }
}