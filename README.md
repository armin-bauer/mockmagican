mock.magican
============

Don't you agree that mocking is somethimes a bit like magic? When I first saw a mocking framework, it seemed to me as if strange magic things were going on in my code (it was jmockit by the way). Now that I have a deeper understanding of the JVM I have been thinging about trying to write a mocking framework myself. 
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

 * Create mocks from interfaces
 * mock static methods
 * Create stubs
 * Create mocks from classes

Since quite some mocking frameworks only support mocks from interfaces, I guess you could say it's production ready with that feature. I want to have stubs though before I can start what I'm trying to do with it.

There will be some tools and fakes for testing, but I don't know about that yet.


