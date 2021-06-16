import { SkiResort } from "./SkiResort";
import { TicketUser } from "./TicketUser";

export interface Tickets{
    id?: number;
    skiResort?: SkiResort;
    typeTicket?: string;
    usingPeriod?: string;
    transportType?: string;
    usingStart?: Date;
    usingEnd?: Date;
    initialPrice?: number;
    ticketUsers?: TicketUser[];
    bill?: number;
    privilege?: string[];
}
