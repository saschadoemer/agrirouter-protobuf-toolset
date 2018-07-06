package de.saschadoemer.agrirouter.protobuf.client.javafx.ui.components;

import agrirouter.request.Request;
import agrirouter.request.payload.endpoint.Capabilities;
import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.google.protobuf.Timestamp;
import de.saschadoemer.agrirouter.protobuf.api.exceptions.CouldNotEncodeProtobufException;
import de.saschadoemer.agrirouter.protobuf.api.exceptions.UUID;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Optional;

public abstract class EncodeMessageWithContentPane extends DefaultGridPane {

    private SimpleStringProperty applicationMessageIdInputProperty;
    private SimpleStringProperty applicationMessageSeqNoProperty;
    private SimpleStringProperty technicalMessageTypeInputProperty;
    private SimpleStringProperty teamSetContextIdInputProperty;
    private SimpleStringProperty modeInputProperty;
    private SimpleStringProperty timestampSecondsInputProperty;
    private SimpleStringProperty timestampNanosInputProperty;

    protected SimpleStringProperty messageContentInputProperty;

    private SimpleStringProperty messageAsBase64OutputProperty;


    public EncodeMessageWithContentPane() {
        this.initProperties();
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.add(new Label("Application Message ID"), 0, 0);
        this.add(new Label("Application Message SeqNo"), 0, 1);
        this.add(new Label("Technical Message Type"), 0, 2);
        this.add(new Label("Team Set Context ID"), 0, 3);
        this.add(new Label("Mode"), 0, 4);
        this.add(new Label("Timestamp - Seconds"), 0, 5);
        this.add(new Label("Timestamp - Nanos"), 0, 6);
        this.add(new Label("Message Content"), 0, 7);
        this.add(new Label("Base64 Encoded Message "), 0, 8);

        this.add(this.createTextField(this.applicationMessageIdInputProperty), 1, 0);
        this.add(this.createTextField(this.applicationMessageSeqNoProperty), 1, 1);
        this.add(this.createTextField(this.technicalMessageTypeInputProperty), 1, 2);
        this.add(this.createTextField(this.teamSetContextIdInputProperty), 1, 3);
        this.add(this.createTextField(this.modeInputProperty), 1, 4);
        this.add(this.createTextField(this.timestampSecondsInputProperty), 1, 5);
        this.add(this.createTextField(this.timestampNanosInputProperty), 1, 6);
        this.add(this.createTextArea(this.messageContentInputProperty), 1, 7);

        this.add(this.createTextField(this.messageAsBase64OutputProperty), 1, 8);

        Button encodeMessageButton = new Button("Encode message");
        encodeMessageButton.setOnAction(event -> {
            this.messageAsBase64OutputProperty.set(BLANK);
            this.updateTimestampProperties();
            if (StringUtils.isNotBlank(this.messageContentInputProperty.get())) {
                try (ByteArrayOutputStream streamedMessage = new ByteArrayOutputStream()) {
                    Request.RequestEnvelope requestEnvelope = this.encodeRequestEnvelope();
                    Request.RequestPayloadWrapper requestPayloadWrapperBuilder = this.encodeRequestPayloadWrapper();
                    requestEnvelope.writeDelimitedTo(streamedMessage);
                    requestPayloadWrapperBuilder.writeDelimitedTo(streamedMessage);
                    this.messageAsBase64OutputProperty.set(Base64.getEncoder().encodeToString(streamedMessage.toByteArray()));
                } catch (IOException | CouldNotEncodeProtobufException e) {
                    this.error("Could not encode message.", e.getMessage());
                }
            }
        });

        this.add(encodeMessageButton, 0, 9);
    }

    private void updateTimestampProperties() {
        this.timestampSecondsInputProperty.set(String.valueOf(Instant.now().atOffset(ZoneOffset.UTC).toEpochSecond()));
        this.timestampNanosInputProperty.set(String.valueOf(Instant.now().atOffset(ZoneOffset.UTC).getNano()));
    }

    private Request.RequestPayloadWrapper encodeRequestPayloadWrapper() {
        Request.RequestPayloadWrapper.Builder requestPayloadWrapperBuilder = Request.RequestPayloadWrapper.newBuilder();
        Any.Builder any = Any.newBuilder();
        any.setTypeUrl(Capabilities.CapabilitySpecification.getDescriptor().getFullName());
        this.encodeMessage().ifPresent(message -> any.setValue(message.toByteString()));
        requestPayloadWrapperBuilder.setDetails(any);
        return requestPayloadWrapperBuilder.build();
    }

    protected abstract Optional<Message> encodeMessage();

    private TextArea createTextArea(Property<String> inputProperty) {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.textProperty().bindBidirectional(inputProperty);
        return textArea;
    }

    private TextField createTextField(Property<String> inputProperty) {
        TextField textField = new TextField();
        textField.setEditable(false);
        textField.textProperty().bindBidirectional(inputProperty);
        return textField;
    }

    private Request.RequestEnvelope encodeRequestEnvelope() {
        Request.RequestEnvelope.Builder requestEnvelope = Request.RequestEnvelope.newBuilder();
        requestEnvelope.setApplicationMessageId(this.applicationMessageIdInputProperty.get());
        requestEnvelope.setApplicationMessageSeqNo(Long.parseLong(this.applicationMessageSeqNoProperty.get()));
        requestEnvelope.setTechnicalMessageType(this.technicalMessageTypeInputProperty.get());
        requestEnvelope.setTeamSetContextId(this.teamSetContextIdInputProperty.get());
        requestEnvelope.setMode(Request.RequestEnvelope.Mode.forNumber(Integer.parseInt(this.modeInputProperty.get())));
        Timestamp.Builder timestamp = Timestamp.newBuilder();
        timestamp.setSeconds(Long.parseLong(this.timestampSecondsInputProperty.get()));
        timestamp.setSeconds(Long.parseLong(this.applicationMessageSeqNoProperty.get()));
        requestEnvelope.setTimestamp(timestamp.build());
        return requestEnvelope.build();
    }

    @Override
    public void initProperties() {
        this.applicationMessageIdInputProperty = new SimpleStringProperty(UUID.generate());
        this.applicationMessageSeqNoProperty = new SimpleStringProperty("1");
        this.technicalMessageTypeInputProperty = new SimpleStringProperty(this.getTechnicalMessageType());
        this.teamSetContextIdInputProperty = new SimpleStringProperty(BLANK);
        this.modeInputProperty = new SimpleStringProperty("0");
        this.timestampSecondsInputProperty = new SimpleStringProperty(String.valueOf(Instant.now().atOffset(ZoneOffset.UTC).toEpochSecond()));
        this.timestampNanosInputProperty = new SimpleStringProperty(String.valueOf(Instant.now().atOffset(ZoneOffset.UTC).getNano()));

        this.messageContentInputProperty = new SimpleStringProperty();

        this.messageAsBase64OutputProperty = new SimpleStringProperty();
    }

    protected abstract String getTechnicalMessageType();

}
