package nl.tudelft.oopp.g72.entities;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputEvent;
import javafx.util.StringConverter;

/**
 * Adapted from Viktoria Jechsmayr,
 * https://github.com/vJechsmayr/FitForFun_prSoftwareEngineering.
 */
public class TimeSpinner extends Spinner<LocalTime> {
    enum Segment {
        HOURS {
            /**
             * Increments time by 'amount of steps' hours.
             *
             * @param time current time
             * @param steps steps
             * @return new time
             */
            @Override
            LocalTime increment(LocalTime time, int steps) {
                return time.plusHours(steps);
            }

            /**
             * Select timespinner.
             *
             * @param spinner timespinner
             */
            @Override
            void select(TimeSpinner spinner) {
                int index = spinner.getEditor().getText().indexOf(':');
                spinner.getEditor().selectRange(0, index);
            }
        },
        MINUTES {
            /**
             * Increments time by 'amount of steps' minutes.
             *
             * @param time  current time
             * @param steps steps
             * @return
             */
            @Override
            LocalTime increment(LocalTime time, int steps) {
                return time.plusMinutes(steps);
            }

            /**
             * Select timespinner.
             *
             * @param spinner timespinner
             */
            @Override
            void select(TimeSpinner spinner) {
                int hrIndex = spinner.getEditor().getText().indexOf(':');
                int minIndex = spinner.getEditor().getText().indexOf(':', hrIndex + 1);
                spinner.getEditor().selectRange(hrIndex + 1, minIndex);
            }
        };

        /**
         * Increments time by steps.
         *
         * @param time current time
         * @param steps steps
         * @return incremented time
         */
        abstract LocalTime increment(LocalTime time, int steps);

        /**
         * Selects timespinner.
         *
         * @param spinner timespinner
         */
        abstract void select(TimeSpinner spinner);

        /**
         * Decrements time by steps.
         *
         * @param time current time
         * @param steps steps
         * @return decremented time
         */
        LocalTime decrement(LocalTime time, int steps) {
            return increment(time, -steps);
        }
    }

    private final ObjectProperty<Segment> sgm = new SimpleObjectProperty<>(Segment.HOURS);

    public ObjectProperty<Segment> segmentObjectProperty() {
        return sgm;
    }

    /**
     * Getter to get segment.
     *
     * @return segment
     */
    public final Segment getSegment() {
        return segmentObjectProperty().get();
    }

    /**
     * Setter to set new segment.
     *
     * @param sgm new segment
     */
    public final void setSegment(Segment sgm) {
        segmentObjectProperty().set(sgm);
    }

    /**
     * Constructor.
     * @param time beginning time
     */
    public TimeSpinner(LocalTime time) {
        setEditable(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        StringConverter<LocalTime> localTimeConverter = new StringConverter<LocalTime>() {

            /**
             * Makes time into string.
             *
             * @param time time
             * @return string
             */
            @Override
            public String toString(LocalTime time) {
                return formatter.format(time);
            }

            /**
             * Takes string and makes intor time.
             *
             * @param string time string
             * @return time
             */
            @Override
            public LocalTime fromString(String string) {
                String[] tokens = string.split(":");
                int hours = getIntField(tokens, 0);
                int minutes = getIntField(tokens, 1);
                return LocalTime.of(hours,minutes);
            }

            /**
             * Getter to get intfield.
             *
             * @param tokens Tokens
             * @param index Index
             * @return IntField
             */
            private int getIntField(String[] tokens, int index) {
                if (tokens.length <= index || tokens[index].isEmpty()) {
                    return 0;
                }
                return Integer.parseInt(tokens[index]);
            }

        };

        TextFormatter<LocalTime> textFormatter = new TextFormatter<LocalTime>(localTimeConverter,
                LocalTime.now(), c -> {
            String newText = c.getControlNewText();
            if (newText.matches("[0-9]{0,2}:[0-9]{0,2}")) {
                return c;
            }
            return null;
        });

        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<LocalTime>() {
            {
                setConverter(localTimeConverter);
                setValue(time);
            }

            /**
             * Decrements by amount of steps.
             *
             * @param steps steps
             */
            @Override
            public void decrement(int steps) {
                setValue(sgm.get().decrement(getValue(), steps));
                sgm.get().select(TimeSpinner.this);
            }

            /**
             * Increments by amount of steps.
             *
             * @param steps steps
             */
            @Override
            public void increment(int steps) {
                setValue(sgm.get().increment(getValue(), steps));
                sgm.get().select(TimeSpinner.this);
            }

        };

        this.setValueFactory(valueFactory);
        this.getEditor().setTextFormatter(textFormatter);

        this.getEditor().addEventHandler(InputEvent.ANY, e -> {
            int caretPos = this.getEditor().getCaretPosition();
            int hrIndex = this.getEditor().getText().indexOf(':');
            if (caretPos <= hrIndex) {
                sgm.set(Segment.HOURS);
            } else {
                sgm.set(Segment.MINUTES);
            }
        });

        sgm.addListener((obs, oldMode, newMode) -> newMode.select(this));
    }

    /**
     * Returns current time.
     */
    public TimeSpinner() {
        this(LocalTime.now());
    }
}