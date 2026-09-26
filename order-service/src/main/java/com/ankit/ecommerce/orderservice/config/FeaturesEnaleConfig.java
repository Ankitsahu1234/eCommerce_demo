
@Configuration
@RefreshScope
@Data
public class FeaturesEnaleConfig {

    @Value("${features.user-tracking-enabled}")
    private boolean isUserTrackingEnabled;
}