mock.magican
============

Don't you agree that mocking is somethimes a bit like magic? When I first saw a mocking framework, it seemed to me as if strange magic things were going on in my code (it was jmockit by the way). Now that I have a deeper understanding of the JVM I have been thinking about trying to write a mocking framework myself. 
I hope the software can help you. You should note that it's still under development and not everything is currently possible with it. What I'm trying to do (and how far I have come with it) will be further down.

About me
---

I'm a software developer and IT enthusiast from Stuttgart, Germany. I've been using computers since I was like 6 years old, got my first machine (an 80268) at 8 and started experimenting with programming languages at 10. Today I'm working at a local software company and I'm mainly using JVM based languages liks Java, Groovy and Scala. I love agile software development and I'm doing TDD whenever possible. 
Since like forever I've been implementing things to get a better understanding of them. 
This time I've decided to try to write a mocking framework.

Why such a stupid name?
----

I thought that would be clear by now. My first impression of powerful mocking framework was "that must be evil black magic or the work of aliens from outer space" (compare to Clarke's third law). So I've decided to tinker around and create a DSL for my Mocking Framework that represents this first impression and uses it as a theme. And basically that's .


How far is it?
----

Okay, I'm just at the beginning. Since I have a current need for a project, I'll probably create stuff in this order:

 * Create mocks from interfaces	(DONE)
 * mock static methods
 * Create stubs
 * Create mocks from classes

Since quite some mocking frameworks only support mocks from interfaces, I guess you could say it's production ready with that feature. I want to have stubs though before I can start what I'm trying to do with it.

There will be some tools and fakes for testing, but I don't know about that yet.


Syntax description 
----

Here's an example on how to mock using this framework. This is a kind of rawish api description and i'll just write some explanations after each line.

	MockWizard.entersTheStage();

The wizard comes on the stage. I guess some applause would be apropriate since i mean a great performer comes on stage to peform for you.

Oh no. An interface, an empty facade, just a shadow of a real object. But we have a need for one of those. So ... basically... let's conjure up one of them using plain magic (and maybe a little bit of java proxy api)

  final MyInterface implementation = MockWizard.conjuresUpA(MyInterface.class);

And yes, it's that easy.

But what good is one of those magic objects we have if we can't do anything with it? Basically the wizard who controlls the strings of fate of all it's creations can foretell what should happen to it.

  MockWizard.foretells().that( () -> implementation.someFunction("hello", "world", 1.0f) ).willBeCalled().andThenReturns("yay it was called");

So the wizard foretold that someFunction was called on the mock object with the given parameters. He foretells that it expects one call to that method and he tells his creation to return a result string then.

Since the foretellings have all been made now the code we want to test can be executed. And then ... it's finally time for the wizard to leave the stage.

  MockWizard.vanishes();

When this happens and the foretelling was not fulfilled, the space-time-continuum will get a disruption and the primitive ways of your jvm will throw an Exception.




