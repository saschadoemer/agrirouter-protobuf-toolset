package de.saschadoemer.agrirouter.protobuf.encoder;

import agrirouter.commons.MessageOuterClass;
import agrirouter.feed.request.FeedRequests;
import agrirouter.feed.response.FeedResponse;
import agrirouter.request.Request;
import agrirouter.request.payload.account.Endpoints;
import agrirouter.request.payload.endpoint.Capabilities;
import agrirouter.request.payload.endpoint.SubscriptionOuterClass;
import com.google.protobuf.util.JsonFormat;

public interface TypeRegistryProvider {

    default JsonFormat.TypeRegistry createTypeRegistry(){
        return JsonFormat.TypeRegistry.newBuilder()
                .add(Request.RequestEnvelope.getDescriptor())
                .add(MessageOuterClass.Messages.getDescriptor())
                .add(Capabilities.CapabilitySpecification.getDescriptor())
                .add(Endpoints.ListEndpointsQuery.getDescriptor())
                .add(agrirouter.response.payload.account.Endpoints.ListEndpointsResponse.getDescriptor())
                .add(FeedResponse.HeaderQueryResponse.getDescriptor())
                .add(FeedRequests.MessageQuery.getDescriptor())
                .add(SubscriptionOuterClass.Subscription.getDescriptor())
                .build();
    }
}
