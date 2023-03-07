# HBL2CachingApp

2nd level cache
============
This caching is associated with "SessionFactory", so we call it as "Global
Cache".
Application will start to search for entity object in the following order
a. L1 cache of current session(if not there)
b. L2 cache of SessionFactory object(if not there)
c. Collect from db and keep in L2 cache and L1 cache then give it to
application.
It is a configurable cache and we can enbale or disable it.
hibernae supports L2 cache through "EHCache"

To configure EHCache in our hibernate projects we use
==============================================
1. Add EHCache jars to the project
2. configure ehcache.xml as shown below
<ehcache>
<diskStore path="java.io.tmpdir"/>
<defaultCache
maxElementsInMemory="100"
eternal="false"
timeToIdleSeconds="10"
timeToLiveSeconds="30"
overflowToDisk="true"
/>
</ehcache>
Also make changes in hibernate.cfg.xml file as shown below
<!-- Configuring EH cache... -->

<property name="hibernate.cache.use_second_level_cache">true</property>
<property
name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegi
onFactory</property>
<property
name="net.sf.ehcache.configurationResourceName">ehcache.xml</property>
3. In the model class inform hiberante to use Caching startegy for Read purpose.
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)//It specifies caching Strategy
public class InsurancePolicy implements Serializable{}
