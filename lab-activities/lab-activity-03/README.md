PCD a.y. 2025-2026 - ISI LM UNIBO - Cesena Campus

# Lab Activity #03 - 20260302

### Implementing Critical Sections in Java

- Using `synchronized` blocks: `pcd.lab03.cs_raw`
- Using locks: `pcd.lab03.cs_withlocks`
    - `Lock` interface and `ReentrantLock` class, part of Java lib for concurrency

### Thread Safety in Java 

- [Lab Notes] Thread Safety
- **Work-in-Lab #01** -  Thread safety - lost update
	- Check lost update problem in `pcd.lab03.lost_updates`
	- Implement a solution 
- **Work-in-Lab #02** -  Thread safety - check and act
	- Check check & act problem in `pcd.lab03.check_act`
	- Implement a solution

### Thread Liveness & Deadlocks

- [Lab Notes] Thread Liveness
- The simplest deadlock in Java: `pcd.lab03.deadlock_simplest.TestDeadlockSimplest`
  - Analysis using VisualVM
- More complex example: AccountManager in pcd.lab04.liveness.accounts 
  - requirements
    - no race conditions in updating individual accounts
    - transfer between accounts should be atomic 
    - maximising concurrency
      - two transactions involving distinct couples of accounts should be served concurrentl
- Deadlocks when implementing MVC and Observer pattern
  - `pcd.lab03.deadlock_obs.TestObsPatternDeadlock`

### Model Checking with Java Path Finder (JPF)

- [About JPF](https://github.com/javapathfinder/jpf-core)
	- [Main docs](https://github.com/javapathfinder/jpf-core/wiki)
	- [NASA web site](https://ti.arc.nasa.gov/tech/rse/vandv/jpf/)
	- [Old JPF sourceforge web site](http://javapathfinder.sourceforge.net/)
- JPF Design and Details
	- [Relationship between JPF and JVM](https://github.com/javapathfinder/jpf-core/wiki/Model-Java-Interface)
	- [Understanding JPF output](https://github.com/javapathfinder/jpf-core/wiki/Understanding-JPF-output)
	- [Top-level design](https://github.com/javapathfinder/jpf-core/wiki/Search-Strategies)
	- [A full perspective about using JPF](https://github.com/javapathfinder/jpf-core/wiki/Different-applications-of-JPF)

- [Installing/Configuring JPF](https://github.com/javapathfinder/jpf-core/wiki/How-to-install-JPF) 
  - Using Docker - Steps:
    1) Open a terminal, choose a directory where work with JPF and clone the JPF repo:

       `git clone https://github.com/javapathfinder/jpf-core.git`
    2) Change directory to `jpf-core`
    3) Build the image:

       `docker-compose build`

    4) Start a container, mounting also a local directory to be used to host the Java projects to be model-checked. In the following the directory is mounted as `/pcd-jpf` inside the container:

       `docker-compose run --rm -v <your local directory>:/pcd-jpf jpf-dev` 

       For instance: mounting the `/pcd-jpf` directory included in the repo (in `lab-activity-03`):

       `docker-compose run --rm -v <path to PCD repo>/pcd-2025-2026/lab-activities/lab-activity-03/pcd-jpf:/pcd-jpf jpf-dev` 


    5) Build JPF

        `./gradlew clean build`

    6) Use JPF - example:

        `java -jar build/RunJPF.jar src/examples/Racer.jpf`
      
- Using JPF
  - Preparing the environment:
    - Start the container mounting the [`pcd-jpf`]() directory included in the repo (in `lab-activity-03`):

   		`docker-compose run --rm -v <path to PCD repo>/pcd-2025-2026/lab-activities/lab-activity-03/pcd-jpf:/pcd-jpf jpf-dev` 
  	- Compile all Java sources:
    
    	`javac -d /pcd-jpf/target/classes -classpath /pcd-jpf/target/classes:/pcd-jpf/lib/jpf.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/*.java`
 
  		(the classpath must include also `jpf.jar` including the Verify API classes)

  - **Example #1** - model-checking sequential programs...
    - sequential program - `pcd.lab03.jpf.TestSequential`
      - `java -jar build/RunJPF.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/TestSequential.jpf`
    - sequential program with input (using Verify API) - `pcd.lab03.jpf.TestSequentialWihtInput`
      - `java -jar build/RunJPF.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/TestSequential.jpf`
    - sequential program with random (using Verify API) - `pcd.lab03.jpf.TestSequentialWihtRand`
      - `java -jar build/RunJPF.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/TestSequential.jpf`
  - **Example #2** - model-checking simple concurrent programs 
    - simple concurrent program - `pcd.lab03.jpf.TestScenarios`
      - `java -jar build/RunJPF.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/TestScenarios.jpf`
      - look at the number of states and traces (scenarios)
    - defining atomic blocks (using Verify API)  
      - `java -jar build/RunJPF.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/TestScenariosWithAtomic.jpf`
      - look at the number of states and traces
  - **Example #3** - Finding lost updates races
    - `pcd.lab03.jpf.TestLostUpdate`
    - `java -jar build/RunJPF.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/TestLostUpdate.jpf`
  - **Example #4** - Finding check-and-act races 
    - `pcd.lab03.jpf.TestCheckAct`
    - `java -jar build/RunJPF.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/TestCheckAct.jpf`
  - **Example #5** - Detecting deadlocks
    - `pcd.lab03.jpf.TestDeadlock`
    - `java -jar build/RunJPF.jar /pcd-jpf/src/main/java/pcd/lab03/jpf/TestDeadlock.jpf`
 