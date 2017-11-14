# Calendar Fun

* [Housekeeping](#housekeeping)
* [Quickstart](#quickstart)
* [Walkthrough](#walkthrough)
* [Todo](#to-do)


# Housekeeping

Please ensure you have a clojure environment running locally - see [Getting Started Guide](https://github.com/tkjone/clojurescript-30#getting-started) if you need to set one up.

# Quickstart

These commands will get the app up and running for local development.  Be sure to run the following comamnds from the root of `34_calendar_fun`.

**run development**

> 1.  Run the projcet

```bash
boot dev
```

> 2.  Visit the app

http://localhost:3000/

Try adding some events.  If you add more than 2 events, and some of them conflict, a message will appear in the browsers dev console.


**run tests**

> 1.  Run the tests

```bash
boot test
```

The above will run tests for the cljc/event module

# Walkthrough

> Design a calendar for someone who works 9am-5pm (work-life balance is built into this calendar :wink:).  Each calendar event is a minimum of 15 minutes and must always be incremented in 15 minute blocks. The killer feature for this app is that you calendar must let users know when their events are conflicting with other events on the calendar.

In our Calendar, an `Event` is stored as a vector with a **start time** and **end time**: `[0 4]`.  In this example, the Event is scheduled for `0 (9:00AM) - 4 (12:00PM)`.  As mentioned, Events are scheduled for 15 minute blocks, so this is also a valid Event: `[0.25 2]` which would be `9:15AM - 10:00AM`.

**Assumptions:**

* The events will not be recieved in order

**Approach**

* Sort the meetings by start time
* If the the start of the second meeting is less than the end of the first meeting this is a conflict

## To Do

* Block user from selecting an end time that is before for the start time + visa-versa
* Cleanup styles.css
* Improve naming conventions
* Answers to questions
  - difference between a list comprehension and for loop
  - why not use map?
  - why not use map and reduce?
  - why not use a loop?
* Have event use timestamps rather than numbers
* Add spec
* Write tests for CLJS code
* Align code to style guide - conistency
* Add events to localstorage
* Add visual indicators so user know there are conflicts
* Improve organizational structure of application
