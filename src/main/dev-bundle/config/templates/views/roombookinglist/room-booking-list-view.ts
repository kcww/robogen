import { html, LitElement } from 'lit';
import { customElement } from 'lit/decorators.js';
import '@vaadin/grid';
import '@vaadin/split-layout';
import '../roombooking/room-booking-view.ts';

@customElement('room-booking-list-view')
export class RoomBookingListView extends LitElement {
  createRenderRoot() {
    return this;
  }

  render() {
    return html`
			<vaadin-split-layout>
				<div class="grid-wrapper">
					<vaadin-grid id="grid"></vaadin-grid>
				</div>
				<room-booking-view id="bookingView"></room-booking-view>
			</vaadin-split-layout>`;
  }
}
