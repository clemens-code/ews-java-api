package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.enumeration.property.ItemFlagStatus;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import org.joda.time.DateTime;

import javax.xml.stream.XMLStreamException;

public class Flag extends ComplexProperty {

  private ItemFlagStatus flagStatus;
  private DateTime startDate;
  private DateTime dueDate;
  private DateTime completeDate;

  public ItemFlagStatus getFlagStatus() {
    return flagStatus;
  }

  public DateTime getStartDate() {
    return startDate;
  }

  public DateTime getDueDate() {
    return dueDate;
  }

  public DateTime getCompleteDate() {
    return completeDate;
  }

  public void setFlagStatus(ItemFlagStatus flagStatus) {
    this.flagStatus = flagStatus;
  }

  public void setStartDate(DateTime startDate) {
    this.startDate = startDate;
  }

  public void setDueDate(DateTime dueDate) {
    this.dueDate = dueDate;
  }

  public void setCompleteDate(DateTime completeDate) {
    this.completeDate = completeDate;
  }

  public Flag() {
  }

  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader) throws Exception {
    String localName = reader.getLocalName();
    if (localName.equals(XmlElementNames.FlagStatus)) {
      this.flagStatus = reader.readElementValue(ItemFlagStatus.class);
      return true;
    }
    if (localName.equals(XmlElementNames.StartDate)) {
      this.startDate = new DateTime(reader.readElementValueAsDateTime());
      return true;
    }
    if (localName.equals(XmlElementNames.DueDate)) {
      this.dueDate = new DateTime(reader.readElementValueAsDateTime());
      return true;
    }
    if (localName.equals(XmlElementNames.CompleteDate)) {
      this.completeDate = new DateTime(reader.readElementValueAsDateTime());
      return true;
    }
    return false;
  }

  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer) throws ServiceXmlSerializationException, XMLStreamException {
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.FlagStatus, this.flagStatus);
    if (flagStatus == ItemFlagStatus.Flagged) {
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.StartDate, this.startDate);
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.DueDate, this.dueDate);
    } else if (this.flagStatus == ItemFlagStatus.Complete) {
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.CompleteDate, this.completeDate);
    }
  }

  public void validate() throws Exception {
    EwsUtilities.validateParam(this.flagStatus, "FlagStatus");
  }
}
