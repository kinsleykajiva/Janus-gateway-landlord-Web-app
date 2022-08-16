package africa.jopen.security;


import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider  {
	public static String INIT_USERNAME = "";
	public static String INIT_PASSWORD = "";

	@Override
	public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
	                                                      AuthenticationRequest<?, ?> authenticationRequest) {
		return Flux.create(emitter -> {
			if ( authenticationRequest.getIdentity().equals(INIT_USERNAME.isEmpty() ? "no_money" :INIT_USERNAME) &&
			     authenticationRequest.getSecret().equals(INIT_PASSWORD.isEmpty() ? "SkillS@Home#ArePretty!":INIT_PASSWORD) ) {
				emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
				emitter.complete();
			} else {
				emitter.error(AuthenticationResponse.exception());
			}
		}, FluxSink.OverflowStrategy.ERROR);
	}
}