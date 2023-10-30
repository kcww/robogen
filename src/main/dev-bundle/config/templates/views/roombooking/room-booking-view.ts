import {html, LitElement} from 'lit';
import {customElement} from 'lit/decorators.js';
import '@vaadin/button';
import '@vaadin/checkbox';
import '@vaadin/combo-box';
import '@vaadin/date-picker';
import '@vaadin/form-layout';
import '@vaadin/grid';
import '@vaadin/horizontal-layout';
import '@vaadin/icon';
import '@vaadin/integer-field';
import '@vaadin/radio-group';
import '@vaadin/select';
import '@vaadin/split-layout';
import '@vaadin/text-field';

@customElement('room-booking-view')
export class RoomBookingView extends LitElement {
  createRenderRoot() {
    return this;
  }

  render() {
    return html`
			<div class="editor-layout">
				<div class="editor">
					<vaadin-form-layout>
						<section aria-labelledby="personal-details-title">
							<h4 id="personal-details-title">Personal Details</h4>
							<vaadin-text-field label="First name" id="firstName" required clear-button-visible
																 autofocus></vaadin-text-field>
							<vaadin-text-field label="Last name" id="lastName" required clear-button-visible></vaadin-text-field>
							<vaadin-text-field label="Email" id="email" required clear-button-visible>
								<vaadin-icon slot="prefix" icon="vaadin:envelope"></vaadin-icon>
							</vaadin-text-field>
							<vaadin-text-field label="Phone" id="phone" required clear-button-visible></vaadin-text-field>
							<vaadin-combo-box label="Country" id="country"></vaadin-combo-box>
						</section>
						<section aria-labelledby="booking-info-title">
							<h4 id="booking-info-title">Booking Information</h4>
							<vaadin-date-picker label="Check-in Date" id="checkinDate"></vaadin-date-picker>
							<vaadin-date-picker label="Check-out Date" id="checkoutDate"></vaadin-date-picker>
							<vaadin-integer-field label="Number of Adults" id="adults" value="1" min="1" max="100"
																		step-buttons-visible></vaadin-integer-field>
							<vaadin-integer-field label="Number of Children" id="children" value="0" min="0" max="100"
																		step-buttons-visible></vaadin-integer-field>
							<vaadin-select label="Room Type" id="roomType"></vaadin-select>
							<vaadin-radio-group label="Smoking Preference" id="smokingPref"></vaadin-radio-group>
						</section>
						<section aria-labelledby="special-requests-title">
							<h4 id="confirmation-title">Special Requests</h4>
							<vaadin-vertical-layout>
								<vaadin-checkbox-group theme="vertical">
									<vaadin-checkbox value="0" label="Late Checkout" id="lateCheckout"></vaadin-checkbox>
									<vaadin-checkbox value="1" label="Early Check-In" id="earlyCheckin"></vaadin-checkbox>
									<vaadin-checkbox value="2" label="Room Upgrade" id="roomUpgrade"></vaadin-checkbox>
								</vaadin-checkbox-group>
								<vaadin-text-field label="Others, please specify." id="otherRequest" clear-button-visible>
								</vaadin-text-field>
							</vaadin-vertical-layout>
						</section>
						<section aria-labelledby="confirmation-title">
							<h4 id="confirmation-title">Confirmation</h4>
							<vaadin-checkbox id="termsAgreed" label="I agree to the Terms and Condition."></vaadin-checkbox>
						</section>
					</vaadin-form-layout>
				</div>
				<vaadin-horizontal-layout class="button-layout">
					<vaadin-button theme="primary" id="submit">Submit</vaadin-button>
					<vaadin-button theme="tertiary" slot="" id="reset">Reset</vaadin-button>
				</vaadin-horizontal-layout>
			</div>`;
  }
}
