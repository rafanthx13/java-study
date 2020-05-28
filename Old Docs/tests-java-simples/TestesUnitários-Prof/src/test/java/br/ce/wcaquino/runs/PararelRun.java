package br.ce.wcaquino.runs;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Assim, eu criei um Runner
public class PararelRun extends BlockJUnit4ClassRunner {

    public PararelRun(Class<?> klass) throws InitializationError {
        super(klass);
        setScheduler(new ThreadPoll());
    }

    // Uma clases para implementar
    private static class ThreadPoll implements RunnerScheduler {

        private ExecutorService executor;

        // USar 5 threads
        public ThreadPoll() {
            executor = Executors.newFixedThreadPool(5);
        }

        // vou pedir para o executar dar um submit no que chegar aqui
        public void schedule(Runnable run) {
            executor.submit(run);
        }

        // Ele dar um shutdown e da 10min pra rodar tudo
        public void finished() {
            executor.shutdown();
            try {
                executor.awaitTermination(10, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }
}
