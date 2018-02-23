package com.sv.millionmeals.reports;

import com.sv.millionmeals.reports.model.TOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by my on 2018-02-21.
 */
public interface ReportRepository extends JpaRepository<TOrderDetails,Integer> {

    @Query(value = "select m_unit.index_no,m_item.name as item,m_unit.name as unit,\n" +
            "ifnull((select SUM(t_order_details.qty) from t_order_details\n" +
            "left join\n" +
            "m_product on m_product.index_no=t_order_details.m_product\n" +
            "where t_order_details.m_product=m.index_no),0) as total_qty,\n" +
            "ifnull((select sum(t_order_details.value)\n" +
            "from t_order_details\n" +
            "left join\n" +
            "m_product on m_product.index_no=t_order_details.m_product\n" +
            "where\n" +
            "t_order_details.m_product=m.index_no),0) as total_value\n" +
            "from\n" +
            "m_product m\n" +
            "left join\n" +
            "m_unit on m_unit.index_no=m.m_unit\n" +
            "left join\n" +
            "m_item on m_item.index_no=m.m_item\n" +
            "left join\n" +
            "t_order_details on t_order_details.m_product=m.index_no\n" +
            "left join\n" +
            "t_order on t_order.index_no=t_order_details.t_order\n" +
            "left join\n" +
            "m_branch on m_branch.index_no = t_order.m_branch\n" +
            "where \n" +
            "date(t_order_details.date) between :fromDate and :toDate\n" +
            "and ('null' = :branch or m_branch.index_no = :branch)\n" +
            "and ('null' = :type or m_item.`type` = :type)\n" +
            "and ('null' = :mainCategory or m_item.m_main_category = :mainCategory)\n" +
            "and ('null' = :subCategory or m_item.m_sub_category = :subCategory)\n" +
            "group by\n" +
            "m.index_no",nativeQuery = true)
    public List<Object> productWise(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("branch") String branch,@Param("mainCategory") String mainCategory,@Param("subCategory") String subCategory,@Param("type") String type);


    @Query(value = "select \n" +
            "i.invoice_no,\n" +
            "CONCAT(t_order.order_type1, \" (\", t_order.order_type2,\")\") as order_type,\n" +
            "t_order.total_sub as item_value,\n" +
            "ifnull(i.service_charge_value,0) as service_charges,\n" +
            "ifnull(i.delivery_charge_value,0) as delivery_charges,\n" +
            "ifnull(i.vat_value,0) as VAT,\n" +
            "ifnull(i.nbt_value,0) as NBT,  \n" +
            "ifnull(t_order.total_tax,0) as total_tax,\n" +
            "t_order.total_amount as gross_value,\n" +
            "ifnull(i.discount_value,0) as discount,\n" +
            "i.final_amount as net_value,\n" +
            "(select sum(t_order.total_sub) from t_order) as total_item_value,\n" +
            "ifnull((select sum(t_invoice.service_charge_value) from t_invoice),0) as total_serive_charge,\n" +
            "ifnull((select sum(t_invoice.delivery_charge_value) from t_invoice),0) as total_delivery_charge,\n" +
            "ifnull((select sum(t_invoice.vat_value) from t_invoice),0) as total_vat,\n" +
            "ifnull((select sum(t_invoice.nbt_value) from t_invoice),0) as total_nbt,\n" +
            "ifnull((select sum(t_order.total_tax) from t_order),0) as total_taxs,\n" +
            "(select sum(t_order.total_amount) from t_order) as total_gross,\n" +
            "ifnull((select sum(t_invoice.discount_value) from t_invoice),0) as total_discount,\n" +
            "ifnull((select sum(t_invoice.final_amount) from t_invoice),0) as total_net\n" +
            " from t_invoice i\n" +
            " left join\n" +
            " t_order on t_order.index_no=i.t_order\n" +
            " where \n" +
            "date(i.date) between :fromDate and :toDate\n" +
            "and ('null' = :branch or t_order.m_branch = :branch)\n" +
            "group by i.index_no",nativeQuery = true)
    public List<Object> invoiceSummery(@Param("fromDate") String fromDate,@Param("toDate") String toDate,@Param("branch") String branch);
}
