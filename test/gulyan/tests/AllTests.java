package gulyan.tests;

import gulyan.ds.QueueTest;
import gulyan.ds.StackTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ QueueTest.class, StackTest.class })
public class AllTests {

}
