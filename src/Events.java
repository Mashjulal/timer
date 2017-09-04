import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Created by Master on 08.03.2017.
 */
public class Events {

    public static final EventType<TimeChangeEvent> TIME_CHANGE =
            new EventType<>(Event.ANY, "TIME_CHANGE");

    public static final EventType<Event> FINISHED_COUNTDOWN =
            new EventType<>(Event.ANY, "FINISHED_COUNTDOWN");

    public static class TimeChangeEvent extends Event {

        public TimeChangeEvent() {
            super(TIME_CHANGE);
        }

        public TimeChangeEvent(Object source, EventTarget target) {
            super(source, target, TIME_CHANGE);
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

    public static class FinishedCountdownEvent extends Event {

        public FinishedCountdownEvent() {
            super(FINISHED_COUNTDOWN);
        }

        public FinishedCountdownEvent(Object source, EventTarget target) {
            super(source, target, FINISHED_COUNTDOWN);
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
