package food;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Supplier;

public record Food(LocalDate expirationDate,
                   Boolean approvedForConsumption,
                   UUID inspectorId) {

    /** Is a food edible to eat
     * @param now LocalDate
     * @return Boolean
     */
    public boolean isEdible(Supplier<LocalDate> now) {
        boolean edible;
        edible = isExpirationDateAfter(now) &&
                this.approvedForConsumption &&
                hasInspectorId();

        return edible;
    }

    /** Does the food object have an inspector id
     * @return Boolean
     */
    private boolean hasInspectorId() {
        return !(this.inspectorId == null);
    }

    /** Is the food past its expiration date?
     * @param now LocalDate
     * @return Boolean
     */
    private boolean isExpirationDateAfter(Supplier<LocalDate> now) {
        return this.expirationDate.isAfter(now.get());
    }
}