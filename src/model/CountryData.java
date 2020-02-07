/*
 * Τμήμα ΗΛΕ 43
 * @author ΒΑΣΙΛΗΣ ΤΣΑΠΑΡΙΚΟΣ - 114307
 * @author ΑΙΚΑΤΕΡΙΝΗ ΚΟΛΕΒΕΝΤΗ - 126971
 * @author ΑΡΙΣΤΕΙΔΗΣ ΦΑΣΟΥΛΑΣ - 100318
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bill
 */
@Entity
@Table(name = "COUNTRY_DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CountryData.findAll", query = "SELECT c FROM CountryData c")
    , @NamedQuery(name = "CountryData.findByDataYear", query = "SELECT c FROM CountryData c WHERE c.dataYear = :dataYear")
    , @NamedQuery(name = "CountryData.findByValue", query = "SELECT c FROM CountryData c WHERE c.value = :value")
    , @NamedQuery(name = "CountryData.findByDataId", query = "SELECT c FROM CountryData c WHERE c.dataId = :dataId")
    , @NamedQuery(name = "CountryData.deleteAll", query = "DELETE  FROM CountryData ")})
public class CountryData implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "DATA_YEAR")
    private String dataYear;
    @Basic(optional = false)
    @Column(name = "VALUE")
    private String value;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DATA_ID")
    private Integer dataId;
    @JoinColumn(name = "DATASET", referencedColumnName = "DATASET_ID")
    @ManyToOne
    private CountryDataset dataset;

    public CountryData() {
    }

    public CountryData(Integer dataId) {
        this.dataId = dataId;
    }

    public CountryData(Integer dataId, String dataYear, String value) {
        this.dataId = dataId;
        this.dataYear = dataYear;
        this.value = value;
    }

    public String getDataYear() {
        return dataYear;
    }

    public void setDataYear(String dataYear) {
        String oldDataYear = this.dataYear;
        this.dataYear = dataYear;
        changeSupport.firePropertyChange("dataYear", oldDataYear, dataYear);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        String oldValue = this.value;
        this.value = value;
        changeSupport.firePropertyChange("value", oldValue, value);
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        Integer oldDataId = this.dataId;
        this.dataId = dataId;
        changeSupport.firePropertyChange("dataId", oldDataId, dataId);
    }

    public CountryDataset getDataset() {
        return dataset;
    }

    public void setDataset(CountryDataset dataset) {
        CountryDataset oldDataset = this.dataset;
        this.dataset = dataset;
        changeSupport.firePropertyChange("dataset", oldDataset, dataset);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataId != null ? dataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountryData)) {
            return false;
        }
        CountryData other = (CountryData) object;
        if ((this.dataId == null && other.dataId != null) || (this.dataId != null && !this.dataId.equals(other.dataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CountryData[ dataId=" + dataId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
