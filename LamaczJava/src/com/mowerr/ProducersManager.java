package com.mowerr;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class ProducersManager {
    private ArrayList<Producer> producers;
    private ArrayList<Thread> threads;

    public void CrackNewFile(String filePath) throws IOException, NoSuchAlgorithmException, InterruptedException {
        StopThreads();

        SetData(filePath);
        ConfigureProducers();
        ConfigureThreads();
        RunProducers();
    }

    public void StopThreads() throws InterruptedException {
        StopProducers();
        JoinThreads();
    }

    private void JoinThreads() throws InterruptedException {
        if(threads == null)
            return;

        for(Thread thread: threads){
            thread.join();
        }
    }

    private void StopProducers() {
        if(producers == null)
            return;

        for(Producer producer: producers){
            producer.Stop();
        }
    }

    private void RunProducers() {
        for(Thread thread: threads){
            thread.start();
        }
    }

    private void ConfigureThreads() {
        threads = new ArrayList<Thread>();

        for(Producer producer: producers){
            threads.add(new Thread(producer));
        }
    }

    private void ConfigureProducers() throws NoSuchAlgorithmException {
        producers = new ArrayList<Producer>();

        AddNewProducer(GuessType.singleWord, Transform.toLower, AffixApplier.suffix);
        AddNewProducer(GuessType.singleWord, Transform.toLower, AffixApplier.prefix);
        AddNewProducer(GuessType.singleWord, Transform.toLower, AffixApplier.doubleAffix);
        AddNewProducer(GuessType.singleWord, Transform.toUpper, AffixApplier.suffix);
        AddNewProducer(GuessType.singleWord, Transform.toUpper, AffixApplier.prefix);
        AddNewProducer(GuessType.singleWord, Transform.toUpper, AffixApplier.doubleAffix);
        AddNewProducer(GuessType.singleWord, Transform.firstUpper, AffixApplier.suffix);
        AddNewProducer(GuessType.singleWord, Transform.firstUpper, AffixApplier.prefix);
        AddNewProducer(GuessType.singleWord, Transform.firstUpper, AffixApplier.doubleAffix);
        AddNewProducer(GuessType.clean, Transform.firstUpper, AffixApplier.noAffix);
        AddNewProducer(GuessType.clean, Transform.toUpper, AffixApplier.noAffix);
        AddNewProducer(GuessType.clean, Transform.toLower, AffixApplier.noAffix);

        AddNewProducer(GuessType.twoWords, Transform.toLower, AffixApplier.suffix);
        AddNewProducer(GuessType.twoWords, Transform.toLower, AffixApplier.prefix);
        AddNewProducer(GuessType.twoWords, Transform.toLower, AffixApplier.doubleAffix);
        AddNewProducer(GuessType.twoWords, Transform.toUpper, AffixApplier.suffix);
        AddNewProducer(GuessType.twoWords, Transform.toUpper, AffixApplier.prefix);
        AddNewProducer(GuessType.twoWords, Transform.toUpper, AffixApplier.doubleAffix);
        AddNewProducer(GuessType.twoWords, Transform.camelCase, AffixApplier.suffix);
        AddNewProducer(GuessType.twoWords, Transform.camelCase, AffixApplier.prefix);
        AddNewProducer(GuessType.twoWords, Transform.camelCase, AffixApplier.doubleAffix);
        AddNewProducer(GuessType.twoWords, Transform.everyFirstUpper, AffixApplier.suffix);
        AddNewProducer(GuessType.twoWords, Transform.everyFirstUpper, AffixApplier.prefix);
        AddNewProducer(GuessType.twoWords, Transform.everyFirstUpper, AffixApplier.doubleAffix);
        AddNewProducer(GuessType.cleanTwoWords, Transform.everyFirstUpper, AffixApplier.noAffix);
        AddNewProducer(GuessType.cleanTwoWords, Transform.camelCase, AffixApplier.noAffix);
        AddNewProducer(GuessType.cleanTwoWords, Transform.toLower, AffixApplier.noAffix);
        AddNewProducer(GuessType.cleanTwoWords, Transform.toUpper, AffixApplier.noAffix);
        
        AddNewProducer(GuessType.numbers, Transform.noTransform, AffixApplier.noAffix);
    }

    private void AddNewProducer(GuessType type, Transform transform, AffixApplier affixApplier) throws NoSuchAlgorithmException {
        Producer newProducer = new Producer.Builder()
                .transform(transform)
                .affixApplier(affixApplier)
                .type(type)
                .Build();

        producers.add(newProducer);
    }

    private void SetData(String filePath) throws IOException {
        PasswordsDB newDB = FileLoader.LoadPasswords(filePath);
        GlobalData.getInstance().setPasswordsDB(newDB);
        GlobalData.getInstance().setCrackingFinished(false);
    }
}