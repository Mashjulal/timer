import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Class-holder for events.
 * Contains two event classes - {@link TimeChangeEvent} and {@link FinishedCountdownEvent};
 * and theirs EventTypes.
 */
class Events {

    // Static EventType constant for TimeChangeEvent
    static final EventType<TimeChangeEvent> TIME_CHANGE =
            new EventType<>(Event.ANY, "TIME_CHANGE");

    // Static EventType constant for FinishedCountDownEvent
    static final EventType<FinishedCountdownEvent> FINISHED_COUNTDOWN =
            new EventType<>(Event.ANY, "FINISHED_COUNTDOWN");


    /**
     * Static class for time change events.
     */
    static class TimeChangeEvent extends Event {

        TimeChangeEvent() {
            super(TIME_CHANGE);
        }

        @Override
        public TimeChangeEvent copyFor(Object newSource, EventTarget newTarget) {
            return (TimeChangeEvent) super.copyFor(newSource, newTarget);
        }

        @Override
        public EventType<? extends TimeChangeEvent> getEventType() {
            return (EventType<? extends TimeChangeEvent>) super.getEventType();
        }
    }

    /**
     * Static class of finished countdown events.
     */
    static class FinishedCountdownEvent extends Event {

        FinishedCountdownEvent() {
            super(FINISHED_COUNTDOWN);
        }

        @Override
        public FinishedCountdownEvent copyFor(Object newSource, EventTarget newTarget) {
            return (FinishedCountdownEvent) super.copyFor(newSource, newTarget);
        }

        @Override
        public EventType<? extends FinishedCountdownEvent> getEventType() {
            return (EventType<? extends FinishedCountdownEvent>) super.getEventType();
        }
    }


}
