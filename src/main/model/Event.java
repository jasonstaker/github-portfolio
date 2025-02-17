package model;

import java.util.List;

// An Event that has a Category and/or Subcategory, start time, end time, name, and reccuring days
public class Event {

    // fields
    private Category category;
    private Subcategory subcategory;
    private Time startTime;
    private Time endTime;
    private String name;
    private List<Day> recurringDays;
    
    // EFFECTS: initializes an Event with given category, subcategory, time, name, and recurring days
    public Event(Category category, Subcategory subcategory, Time startTime, 
                Time endTime, String name, List<Day> recurringDays) {
        this.category = category;
        this.subcategory = subcategory;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.recurringDays = recurringDays;

        for (Day d: recurringDays) {
            d.addEvent(this);
        }
        
    }

    // EFFECTS: returns true if the event happens more than once
    public boolean isRecurring() {
        return recurringDays.size() > 1;
    }

    // EFFECTS: returns true if the this Event occurs on the given day
    public boolean occursOn(Day day) {
        return recurringDays.contains(day);
    }

    // MODIFIES: this
    // EFFECTS: adds the given day to the recurring days in ascending order if it is not there, nothing otherwise
    public void addRecurringDay(Day addDay) {
        int index = 0;

        for (int i = 0; i < recurringDays.size(); i++) {
            Day day = recurringDays.get(i);
            
            if (addDay.isBefore(day)) {
                break;
            }

            index++;

        }

        if (index == recurringDays.size()) {
            recurringDays.add(addDay);
        } else {
            recurringDays.add(index, addDay);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the given day from the recurring days if it exists, nothing otherwise
    public void removeRecurringDay(Day removedDay) {
        recurringDays.remove(removedDay);
        removedDay.removeEvent(this);
    }

    /*
     * GETTERS/SETTERS
     */

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public List<Day> getRecurringDays() {
        return recurringDays;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category)  {
        this.category = category;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public void setRecurringDays(List<Day> recurringDays) {
        this.recurringDays = recurringDays;
        for (Day day: recurringDays) {
            day.addEvent(this);
        }
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

}